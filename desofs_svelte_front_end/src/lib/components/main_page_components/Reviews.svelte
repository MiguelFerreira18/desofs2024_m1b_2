<script lang="ts">
	import SmallCard from './SmallCard.svelte';
	import type { Review } from '$lib/Types/types';
	export let reviews: Review[];
	// let array = [1, 2, 3, 4, 5];
</script>

<div class="grid grid-cols-3 sm:grid-cols-5 lg:grid-flow-col-6 gap-3 bg-gray-100">
	{#each reviews as review}
		<SmallCard {review} />
	{/each}
</div>
