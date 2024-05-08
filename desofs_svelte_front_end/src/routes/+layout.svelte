<script lang="ts">
	import '../app.css';
	import Footer from '$lib/components/default_components/Footer.svelte';
	import Header from '$lib/components/default_components/Header/Header.svelte';
	import type { LayoutServerData } from './$types';

	export let data: LayoutServerData

</script>

<Header {data}/>
<slot />
<Footer year="2024" />
