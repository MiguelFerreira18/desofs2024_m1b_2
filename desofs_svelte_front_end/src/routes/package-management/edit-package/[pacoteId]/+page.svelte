<script lang="ts">
	import { goto } from '$app/navigation';
	import { apiConfig } from '../../../config/api';
    import type { PageData } from './$types';
    export let data: PageData;


    const apiUrl = apiConfig.baseUrl;
    type PackageDTOPatchSend = {
        pacoteId: number;
        nome: string;
        pacoteDescription: string;
        pacoteBasePrice: number;
        disabled: boolean;
        tipoPacote: number;
    };
    


    let packageId = data.pacote.pacoteId;
    let name = data.pacote.nome;
	let price = data.pacote.pacoteBasePrice;
	let description = data.pacote.pacoteDescription;
	let disabled = data.pacote.disabled;
	let tipoPacote = data.tipoPacotes.find((type) => type.tipoPacoteId === data.pacote.tipoPacote.tipoPacoteId)?.tipoPacoteId;


    $: console.log(disabled);

    async function handleSubmit() {
        if(tipoPacote === undefined) {
            tipoPacote = 0
        }
        const packageData: PackageDTOPatchSend = {
            pacoteId: packageId,
            nome: name,
            pacoteDescription: description,
            pacoteBasePrice: price,
            disabled: disabled,
            tipoPacote: tipoPacote
        };

        console.log(packageData);

        

        const response = await fetch(`${apiUrl}/pacote/update`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(packageData)
        });

        if (response.ok) {
            goto('/package-management');
        } else {
            console.error('Failed to save package');
        }
    }

    function handleCancel() {
        console.log('cancel');
        goto('/package-management');
    }

</script>


<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<h1 class="text-5xl font-bold mb-8 text-center">Create Package</h1>
	<form class="grid grid-rows-3 border rounded bg-white px-20 py-12 gap-16">
		<div class="flex min-h-10 justify-evenly">
			<div class="flex flex-col gap-2 content-center">
				<label for="name">Name</label>
				<input
					bind:value={name}
					type="text"
					name="name"
					id="name"
					class="rounded border border-current p-1"
				/>
			</div>
			<div class="flex flex-col gap-2 content-center">
				<label for="price">Price</label>
				<input
					bind:value={price}
					type="number"
					name="price"
					id="price"
					min="0"
					class="rounded border border-current p-1"
				/>
			</div>
			<div class="flex flex-col gap-2 content-center">
				<label for="description">Description</label>
				<textarea
					bind:value={description}
					name="description"
					id="description"
					class="rounded border border-current w-80 p-1"
				></textarea>
			</div>
		</div>
		<div class="flex min-h-10 justify-evenly">
			<div class="content-center">
				<input
					bind:checked={disabled}
					type="checkbox"
					name="isDisabled"
					id="isDisabled "
					class="mr-2 rounded border border-current accent-black size-4"
				/>
				<label for="isDisabled">isDisabled</label>
			</div>
			<div class="flex flex-col gap-2 content-center">
				<!-- dropdow -->
				<label for="tipoPacote">Tipo pacote</label>
				<select
					bind:value={tipoPacote}
					name="tipoPacote"
					id="tipoPacote"
					class="rounded border border-current p-2 bg-white"
				>
					<option value="">Choose a type</option>
					{#each data.tipoPacotes as type}
						<option value={type.tipoPacoteId}>{type.nome}</option>
					{/each}
				</select>
			</div>
		</div>
		<div class="flex flex-row gap-2 justify-center max-h-12">
			<!-- 2 buttons -->
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
