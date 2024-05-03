<script lang="ts">
	import type { PageData } from './$types';
	import Card from '$lib/components/main_page_components/Card.svelte';
	export let data: PageData;
</script>

<div class="bg-gray-100 px-20 pt-10 min-h-screen">
	<div class="grid grid-cols-2 border rounded bg-white px-20 py-12 gap-5">
		{#each data.enabledPackages as plano}
			<Card singlePackage={plano} />
		{/each}
	</div>
</div>
