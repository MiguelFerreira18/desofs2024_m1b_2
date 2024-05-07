<script lang="ts">
	import { goto } from '$app/navigation';
	import { page } from '$app/stores';
	import { apiConfig } from '../../config/api';
	import type { PageData } from './$types';
	import type { ReviewDTOSend } from '$lib/Types/types';

	const apiUrl = apiConfig.baseUrl;
	let planoId = $page.params.planoId;
	let rating = 0;
	let review = '';

	async function handleSubmit() {
		const reviewData: ReviewDTOSend = {
			rating: rating,
			reviewText: review,
			pacote: parseInt(planoId),
			user: 1 //TODO Mudar isto para quando se tiver o login feito
		};

		const response = await fetch(`${apiUrl}/review/save`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(reviewData)
		});

		if (response.ok) {
			goto('/planos/' + planoId);
		} else {
			console.error('Failed to create review');
		}
	}

	export let data: PageData;
</script>

<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<div class="border rounded bg-white px-20 py-12 grid grid-cols-2">
		<div class="my-auto max-w-md border-2 border-gray-200 rounded-lg shadow-lg p-5 bg-slate-100">
			<!-- Header of card -->
			<div class="flex flex-col items-center">
				<h1 class="text-3xl font-bold text-center">{data.pacote.nome}</h1>
			</div>

			<!-- Body of card -->
			<div class="mt-20 p-5">
				<div class="flex flex-col items-center">
					<h1 class="text-2xl font-bold text-center">Descrição</h1>
					<p class="text-center">{data.pacote.pacoteDescription}</p>
				</div>
				<div class="flex flex-col items-center mt-10">
					<h1 class="text-2xl font-bold text-center">Valor</h1>
					<p class="text-center">{data.pacote.pacoteBasePrice} €</p>
				</div>
				<div class="flex flex-col items-center mt-10">
					<h1 class="text-2xl font-bold text-center">Tipo pacote</h1>
					<p class="text-center">{data.pacote.tipoPacote.nome}</p>
				</div>
			</div>
		</div>
		<div class="flex flex-col gap-5 pr-40">
			<p id="number-of-characters">{review.length}/128</p>
			<textarea
				name="reviewText"
				id="review-text"
				class="rounded border border-current p-1 resize-y"
				placeholder="Your Review Here"
				bind:value={review}
				maxlength="128"
			/>

			<input bind:value={rating} type="range" name="rating" id="rating" min="0" max="5" />
			<p id="number-of-stars">{rating}/5</p>

			<button
				on:click={handleSubmit}
				class="inline-block rounded border border-current px-4 py-2 text-sm">Create Review</button
			>
		</div>
	</div>
</div>
