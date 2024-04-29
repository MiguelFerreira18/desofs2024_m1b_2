<script>
	import '../app.css';
	import Footer from '$lib/components/default_components/Footer.svelte';
	import Header from '$lib/components/default_components/Header/Header.svelte';
</script>

<Header />
<slot />
<Footer year="2024" />
