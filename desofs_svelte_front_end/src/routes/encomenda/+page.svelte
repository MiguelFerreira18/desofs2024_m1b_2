<script lang="ts">
	import type { PageData } from './$types';
	import Tags from '$lib/components/Tags.svelte';
	import { goto, invalidateAll } from '$app/navigation';
	import { sendRequest } from '$lib/scripts';

	export let data: PageData;

	async function deleteDelivery(encomendaId: number) {
		const response = await sendRequest(`encomenda/delete/${encomendaId}`, 'DELETE', '', data.user.token);

		if (response.ok) {
			goto('/encomenda');
		} else {
			console.error('Error deleting delivery');
		}

		await invalidateAll();
	}
</script>

<!-- 2 partes horizontais -->
<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<div class="flex flex-col border rounded bg-white px-20 py-12 gap-5">
		<div class="flex">
                <div style="flex-grow: 1;text-align: end;">
                    <button style="margin-left: auto;"
                        class="ml-auto inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
                        on:click={() => goto('/encomenda/create-encomenda')}
                        ><i class="fa-solid fa-plus"></i></button
                    >
                </div>
		</div>
		<table class="table-auto text-center">
			<thead>
				<tr class="h-12">
					<th class="border text-xl">Meals</th>
					<th class="border text-xl">People</th>
					<th class="border text-xl">Price</th>
					<th class="border text-xl">Date</th>
					<th class="border text-xl">Package Id</th>
                    <th class="border text-xl">State</th>
					<th class="border text-xl">Actions</th>
				</tr>
			</thead>
			<tbody>
				{#each  data.encomendas as item}
					<tr class="h-13">
						<td class="border py-5">{item.mealsPerWeek}</td>
						<td class="border py-5">{item.numberOfPeople}</td>
						<td class="border py-5">{item.price}</td>
						<td class="border py-5">{item.dataEncomenda}</td>
						<td class="border py-5">{item.pacote.pacoteId}</td>
						<td class="border py-5">{item.estado}</td>
						<td class="border py-5">
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => goto(`/encomenda/edit-encomenda/${item.encomendaId}`)}
								><i class="fa-solid fa-pen-to-square"></i></button
							>
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => deleteDelivery(item.encomendaId)}
								><i class="fa-solid fa-trash"></i></button
							>
						</td>
					</tr>
				{/each}
			</tbody>
		</table>
	</div>
</div>
