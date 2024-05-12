<script lang="ts">
    import type { PageData } from './$types';
	import { goto } from '$app/navigation';
	import type { DeliveryDTOSend } from '$lib/Types/types';
	import { sendRequest } from '$lib/scripts';
    import { EstadoEncomenda } from '$lib/Enum/enums';

    export let data: PageData;

    let meals = 1;
	let people = 1;
	let price = 1;
	let pacoteId = 0;
    
    async function handleSubmit() {
		const encData: DeliveryDTOSend = {
			mealsPerWeek: meals,
			numberOfPeople: people,
			price: price,
			pacoteId: pacoteId,
			dataEncomenda: new Date().toISOString(),
            estado: EstadoEncomenda.REGISTADO,
            userId: data.user.userId
		};

		const response = await sendRequest(
			`encomenda/save`,
			'POST',
			JSON.stringify(encData),
			data.user.token
		);

		if (response.ok) {
			goto('/encomenda');
		} else {
			console.error('Failed to save delivery');
		}
	}

    async function handleCancel() {
		window.location.href = '/encomenda';
	}

</script>

<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<h1 class="text-5xl font-bold mb-8 text-center">Create Delivery</h1>
	<form class="grid grid-rows-3 border rounded bg-white px-20 py-12 gap-16">
		<div class="flex flex-col gap-2 min-h-10 justify-evenly">
			<div class="flex flex-row gap-2 content-center items-center">
				<label class="min-w-40" for="meals">Number of Meals</label>
				<input
					bind:value={meals}
					type="number"
					name="meals"
					id="meals"
					class="rounded border border-current p-1 min-w-80"
                    min="1"
                    max="7"
				/>
			</div>
			<div class="flex flex-row gap-2 content-center items-center">
				<label class="min-w-40" for="people">Number of People</label>
				<input 
					bind:value={people}
					type="number"
					name="people"
					id="people"
					class="rounded border border-current p-1 min-w-80"
                    min="1"
                    max="5"
				/>
			</div>
			<div class="flex flex-row gap-2 content-center items-center">
				<label class="min-w-40" for="price">Price</label>
				<input
					bind:value={price}
                    type= "number"
					name="price"
					id="price"
					class="rounded border border-current p-1 min-w-80"
                    min="1"
				/>
			</div>
            <div class="flex flex-row gap-2 content-center items-center">
				<label class="min-w-40" for="pacote">Pacote</label>
				<select
					bind:value={pacoteId}
					name="pacote"
					id="pacote"
					class="rounded border border-current p-2 bg-white min-w-80"
				>
					{#each data.pacotes as type}
						<option value={type.pacoteId}>{type.nome}</option>
					{/each}
				</select>
			</div>
		</div>
		<div class="flex flex-row gap-2 justify-center max-h-12">
			<button
				on:click={handleCancel}
				class="inline-block rounded border px-4 py-2 bg-rose-600 hover:bg-red-600 transition-colors duration-300"
				>Cancel</button
			>
			<button
				on:click={handleSubmit}
				class="inline-block rounded border px-4 py-2 bg-sky-600 hover:bg-blue-600 transition-colors duration-300"
				>Add</button
			>
		</div>
	</form>
</div>