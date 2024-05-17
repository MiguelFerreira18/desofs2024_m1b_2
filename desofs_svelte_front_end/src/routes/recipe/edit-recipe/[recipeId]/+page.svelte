<script lang="ts">
	import type { PageData } from './$types';
	import { goto } from '$app/navigation';
	import type { RecipePatchSend } from '$lib/Types/types';
	import { sendRequest } from '$lib/scripts';
	export let data: PageData;

	let name = data.recipe.nome;
	let path = data.recipe.path;
	let pacoteId = data.recipe.pacote.pacoteId;
	let recipeTypeId = data.recipe.tipoReceita.tipoReceitaId;

	async function handleSubmit() {
		const encData: RecipePatchSend = {
			receitaId: data.recipe.receitaId,
			nome: name,
            path: path,
            pacote: pacoteId,
            tipoReceita: recipeTypeId
		};

		const response = await sendRequest(
			`receita/update`,
			'PATCH',
			JSON.stringify(encData),
			data.user.token
		);

		if (response.ok) {
			goto('/recipe');
		} else {
			console.error('Failed to update recipe');
		}
	}

	async function handleCancel() {
		window.location.href = '/recipe';
	}
</script>

<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<h1 class="text-5xl font-bold mb-8 text-center">Edit Recipe</h1>
	<form class="grid grid-rows-3 border rounded bg-white px-20 py-12 gap-16">
		<div class="flex flex-col gap-2 min-h-10 justify-evenly">
			<div class="flex flex-row gap-2 content-center items-center">
				<label class="min-w-40" for="meals">Name</label>
				<input
                    bind:value={name}
                    type="text"
                    name="name"
                    id="name"
                    class="rounded border border-current p-1 min-w-80"
				/>
			</div>
			<div class="flex flex-row gap-2 content-center items-center">
				<label class="min-w-40" for="path">Path</label>
				<input
					bind:value={path}
					type="text"
					name="path"
					id="path"
					class="rounded border border-current p-1 min-w-80"
				/>
			</div>
			<div class="flex flex-row gap-2 content-center items-center">
				<label class="min-w-40" for="tipoReceita">Recipe Type</label>
				<select
					bind:value={recipeTypeId}
					name="tipoReceita"
					id="tipoReceita"
					class="rounded border border-current p-2 bg-white min-w-80"
				>
                    {#each data.tipoReceitas as type}
						<option value={type.tipoReceitaId}>{type.nome}</option>
					{/each}
                </select>
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
				>Update</button
			>
		</div>
	</form>
</div>
