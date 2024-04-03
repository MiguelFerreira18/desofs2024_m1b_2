<script lang="ts">
	import SmallCard from "./SmallCard.svelte";


</script>



<div class="grid grid-cols-3 sm:grid-cols-5 lg:grid-flow-col-6 gap-3 bg-gray-100">
	{#each Array(5) as _, index (index)}
		<SmallCard />
	{/each}
</div>