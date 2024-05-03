<script lang="ts">
	import { goto } from '$app/navigation';
	import type { Package } from '$lib/Types/types';

	export let singlePackage: Package;
</script>

<div
	class="bg-white rounded shadow-lg border hover:shadow-2xl hover:scale-105 transition duration-300"
>
	<div class="px-10 py-6 text-center flex flex-col items-center justify-center content-center">
		<h1>{singlePackage.tipoPacote.nome}</h1>
		<div class="text-2xl font-bold text-green-400 mb-4">{singlePackage.nome}</div>
		<span class="text-sm">
			{singlePackage.pacoteDescription}
		</span>
		<div class="text-2xl font-bold text-green-400 mb-4">{singlePackage.pacoteBasePrice}€</div>
		<button
			on:click={() => goto(`/planos/${singlePackage.pacoteId}`)}
			class="bg-green-400 hover:bg-green-500 text-white font-bold py-2 px-4 rounded"
		>
			Comprar
		</button>
	</div>
</div>
