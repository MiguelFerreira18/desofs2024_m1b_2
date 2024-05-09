<script lang="ts">
	import '../app.css';
	import Footer from '$lib/components/default_components/Footer.svelte';
	import Header from '$lib/components/default_components/Header/Header.svelte';
	import type { User } from '$lib/Types/types';

	export let data: User;
</script>

<Header {data} />
<slot />
<Footer year="2024" />
