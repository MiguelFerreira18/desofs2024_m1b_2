<script lang="ts">
	import type { PageData } from './$types';
	import { goto, invalidateAll } from '$app/navigation';
	import { sendRequest } from '$lib/scripts';

	export let data: PageData;

	async function deleteRecipe(recipeId: number) {
		const response = await sendRequest(`receita/delete/${recipeId}`, 'DELETE', '', data.user.token);

		if (response.ok) {
			goto('/recipe');
		} else {
			console.error('Error deleting recipe');
		}

		await invalidateAll();
	}

	async function downloadRecipe(recipeId: number) {
		const response = await sendRequest(`receita/download/${recipeId}`, 'GET', '', data.user.token);
		if (!response.ok) {
			console.error('Error downloading recipe');
		}
		const contentDisposition = response.headers.get('Content-Disposition');
		let filename = 'recipe';
		if (contentDisposition) {
			const filenameMatch = contentDisposition.match(/filename="?(.+)"?/);
			if (filenameMatch && filenameMatch.length === 2) {
				filename = filenameMatch[1];
			}
		}
		const blob = await response.blob();
		const url = window.URL.createObjectURL(blob);
		const link = document.createElement('a');
		link.href = url;
		link.download = filename;
		document.body.appendChild(link);
		link.click();
		document.body.removeChild(link);
		window.URL.revokeObjectURL(url);
	}
</script>

<!-- 2 partes horizontais -->
<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<div class="flex flex-col border rounded bg-white px-20 py-12 gap-5">
		<div class="flex">
			<div style="flex-grow: 1;text-align: end;">
				<button
					style="margin-left: auto;"
					class="ml-auto inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
					on:click={() => goto('/recipe/create-recipe')}><i class="fa-solid fa-plus"></i></button
				>
			</div>
		</div>
		<table class="table-auto text-center">
			<thead>
				<tr class="h-12">
					<th class="border text-xl">Package</th>
					<th class="border text-xl">Recipe Type</th>
					<th class="border text-xl">Path</th>
					<th class="border text-xl">Name</th>
					<th class="border text-xl">Actions</th>
				</tr>
			</thead>
			<tbody>
				{#each data.recipes as item}
					<tr class="h-13">
						<td class="border py-5">{item.pacote.pacoteId}</td>
						<td class="border py-5">{item.tipoReceita.tipoReceitaId}</td>
						<td class="border py-5">{item.path}</td>
						<td class="border py-5">{item.nome}</td>
						<td class="border py-5">
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => goto(`/recipe/edit-recipe/${item.receitaId}`)}
								><i class="fa-solid fa-pen-to-square"></i></button
							>
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => deleteRecipe(item.receitaId)}
								><i class="fa-solid fa-trash"></i></button
							>
							<button
								class="inline-block rounded border px-4 py-2 bg-white hover:bg-gray-200 transition-colors duration-300"
								on:click={() => downloadRecipe(item.receitaId)}
								><i class="fa-solid fa-download"></i></button
							>
						</td>
					</tr>
				{/each}
			</tbody>
		</table>
	</div>
</div>
