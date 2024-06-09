<script lang="ts">
	import type { PageData } from './$types';
	import Hero from '$lib/components/main_page_components/Hero.svelte';
	import MenuCards from '$lib/components/main_page_components/MenuCards.svelte';
	import Reviews from '$lib/components/main_page_components/Reviews.svelte';
	import type { Package, Review } from '$lib/Types/types';
	import Modal from '$lib/components/default_components/Modal.svelte';

	export let data: PageData;
	const pacotes: Package[] = data.enabledPackages || [];

	const reviews: Review[] = data.reviews || [];
</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>

<section class="bg-gray-100 px-20">
	<Modal showModal={true}>
		<h2 slot="header" class="text-3xl">
			Terms and Services
		</h2>

		<ol class="definition-list list list-disc pl-2 pr-2">
			<li>Autoriza a utlização de dados</li>
			<li>
				Autoriza a utilização de dados para a construção de uma base de conhecimento
			</li>
			<li>
				Autoriza a modificação e a utilização de dados para a construção de uma base de
				conhecimento
			</li>
		</ol>
	</Modal>
	<Hero />
	{#if pacotes.length > 0}
		<MenuCards packages={pacotes} />
		<span class="relative flex justify-center">
			<div
				class="absolute inset-x-0 top-1/2 h-px -translate-y-1/2 bg-transparent bg-gradient-to-r from-transparent via-gray-500 to-transparent opacity-75"
			></div>
			<span class="relative z-10 text-2xl bg-gray-100 px-6">Opinião dos consumidores</span>
		</span>
	{:else}
		<h3 class="text-2xl text-center">Em breve teremos novos pacotes</h3>
	{/if}
	{#if reviews.length > 0}
		<Reviews {reviews} />
	{:else}
		<div class="flex justify-center">Seja o primeiro a adicionar a sua opinião</div>
	{/if}
</section>
