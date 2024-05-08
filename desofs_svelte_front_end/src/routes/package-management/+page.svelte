<script lang="ts">
	import type { PageData } from './$types';
	import Tags from '$lib/components/Tags.svelte';
	import { goto, invalidateAll } from '$app/navigation';
	import { apiConfig } from '../config/api';

	const apiUrl = apiConfig.baseUrl;

	export let data: PageData;

	let searchTerm = '';
	let isDisabled = false;

	$: filteredData = data.packages.filter((componente) => {
		const term = searchTerm.toLowerCase();
		return (
			(componente.nome.toLowerCase().includes(term) ||
				componente.pacoteDescription.toLowerCase().includes(term) ||
				componente.pacoteBasePrice.toString().includes(term) ||
				componente.tipoPacote.nome.toLowerCase().includes(term)) &&
			componente.disabled === isDisabled
		);
	});

	async function deletePacote(pacoteId: number) {
		const response = await fetch(`${apiUrl}/pacote/delete/${pacoteId}`, {
			method: 'DELETE',
			headers: {
				'Content-Type': 'application/json'
			}
		});

		if (response.ok) {
			goto('/package-management');
		} else {
			console.error('Erro a apagar o pacote');
		}

		await invalidateAll();
	}
</script>

<!-- 2 partes horizontais -->
<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<div class="flex flex-col border rounded bg-white px-20 py-12 gap-5">
		<div class="flex min-h-10 max-h-20 items-center">
			<div class="flex">
				<div>
					<input
						type="checkbox"
						name="isDisabled"
						id="is-disabled"
						class="mr-2 rounded border border-current accent-black"
						bind:checked={isDisabled}
					/>
					<label for="is-disabled">isDisabled</label>
				</div>
				<div class="pl-8 ml-8">
					<input
						type="search"
						name="search"
						id="search"
						class="rounded border border-current"
						placeholder="Search"
						bind:value={searchTerm}
					/>
				</div>
			</div>
			<button
				class="ml-auto inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
				on:click={() => goto('/package-management/create-package')}
				><i class="fa-solid fa-plus"></i></button
			>
		</div>
		<table class="table-auto text-center">
			<thead>
				<tr class="h-12">
					<th class="border text-xl">Name</th>
					<th class="border text-xl">Description</th>
					<th class="border text-xl">Price</th>
					<th class="border text-xl">Tipo do Pacote</th>
					<th class="border text-xl">Disabled</th>
					<th class="border text-xl">Actions</th>
				</tr>
			</thead>
			<tbody>
				{#each filteredData as componente}
					<tr class="h-13">
						<td class="border py-5">{componente.nome}</td>
						<td class="border py-5">{componente.pacoteDescription}</td>
						<td class="border py-5">{componente.pacoteBasePrice}</td>
						<td class="border py-5">{componente.tipoPacote.nome}</td>
						<td class="border py-5"><Tags disabled={componente.disabled} /></td>
						<td class="border py-5">
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => goto(`/package-management/edit-package/${componente.pacoteId}`)}
								><i class="fa-solid fa-pen-to-square"></i></button
							>
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => deletePacote(componente.pacoteId)}
								><i class="fa-solid fa-trash"></i></button
							>
						</td>
					</tr>
				{/each}
			</tbody>
		</table>
	</div>
</div>
