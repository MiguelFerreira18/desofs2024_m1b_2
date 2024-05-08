<script lang="ts">
	import type { Package } from '$lib/Types/types';
	import Card from './Card.svelte';
	export let packages: Package[];

</script>

<div id="planos" class="flex flex-col items-center py-5">
	<h1 class="text-6xl font-semibold font-sans">Escolhe a tua paixão</h1>
	<div class="grid grid-cols-2 gap-7 py-10 sm:grid-cols- lg:grid-flow-col-4">
		{#each packages as singlePackage}
			<Card {singlePackage} />
		{/each}
	</div>
</div>
