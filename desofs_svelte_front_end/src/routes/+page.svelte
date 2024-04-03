<script>
	import Hero from "$lib/components/main_page_components/Hero.svelte";
	import MenuCards from "$lib/components/main_page_components/MenuCards.svelte";
	import Reviews from "$lib/components/main_page_components/Reviews.svelte";




</script>

<svelte:head>
	<title>Home</title>
	<meta name="description" content="Svelte demo app" />
</svelte:head>

<section class="bg-gray-100 px-20">
<Hero/>
<MenuCards/>
<span class="relative flex justify-center">
	<div
	  class="absolute inset-x-0 top-1/2 h-px -translate-y-1/2 bg-transparent bg-gradient-to-r from-transparent via-gray-500 to-transparent opacity-75"
	></div>
	<span class="relative z-10 text-2xl bg-gray-100 px-6">Opinião dos consumidores</span>
  </span>  
<Reviews/>

</section>

