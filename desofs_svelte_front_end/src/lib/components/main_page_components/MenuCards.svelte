<script lang="ts">
	import Card from './Card.svelte';

	let array = [1, 2, 3, 4];
</script>

<div id="planos" class="flex flex-col items-center py-5">
	<h1 class="text-6xl font-semibold font-sans">Escolhe a tua paixão</h1>
	<div class="grid grid-cols-2 gap-7 py-10 sm:grid-cols-4 lg:grid-flow-col-4">
		{#each array as index}
			<Card key={index} />
		{/each}
	</div>
</div>
