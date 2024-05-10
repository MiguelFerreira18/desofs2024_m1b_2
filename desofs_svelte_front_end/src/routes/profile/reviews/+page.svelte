<script lang="ts">
	import { goto, invalidateAll } from '$app/navigation';
	import { sendRequest } from '$lib/scripts';
	import type { PageData } from './$types';
	export let data: PageData;

	async function deleteReview(pacoteId: number) {
		const res = await sendRequest(`review/delete/${pacoteId}`, 'DELETE', '', data.user.token);

		if (res.ok) {
			goto('reviews');
		} else {
			alert('Failed to delete pacote');
		}
		await invalidateAll();
	}
</script>

<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<div class="flex flex-col border rounded bg-white px-20 py-12 gap-5">
		<table class="table-auto text-center">
			<thead>
				<tr class="h-12">
					<th class="border text-xl">Review</th>
					<th class="border text-xl">rating</th>
					<th class="border text-xl">Pacote</th>
					<th class="border text-xl">Actions</th>
				</tr>
			</thead>
			<tbody>
				{#each data.reviews as review}
					<tr class="h-13">
						<td class="border py-5">{review.reviewText}</td>
						<td class="border py-5">{review.rating}</td>
						<td class="border py-5">{review.pacote.nome}</td>
						<td class="border py-5">
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => goto(`reviews/edit-review/${review.reviewId}`)}
								><i class="fa-solid fa-pen-to-square"></i></button
							>
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => deleteReview(review.reviewId)}
								><i class="fa-solid fa-trash"></i></button
							>
						</td>
					</tr>
				{/each}
			</tbody>
		</table>
	</div>
</div>
