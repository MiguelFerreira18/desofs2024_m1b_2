<script lang="ts">
	import Button from './Button.svelte';
	import { goto } from '$app/navigation';
	export let title: String;
	let loggedIn = false;
	let isAdmin = false;
	let isDocumentManager = false;

	let isMenuOpen = false;
</script>

<nav class="flex justify-evenly flex-row bg-white border-gray-200 px-24 py-4">
	<div class="flex flex-row self-center gap-2 mr-auto">
		<a href="/"><p>logo</p></a>
		<a href="/"><p>Cozinha na Cozinha</p></a>
	</div>

	<ul class="flex justify-center self-center flex-row gap-8 mr-auto">
		<li><a href="#">Planos</a></li>
		<li><a href="/planos">Sobre nós</a></li>
	</ul>

	<div class="self-center">
		{#if loggedIn}
			<Button on:click={() => goto('/profile')} className="profile-button" text="Perfil" />
			<Button on:click={() => goto('/Carrinho')} className="order-button" text="Carrinho" />

			{#if isDocumentManager || isAdmin}
				<Button
					on:click={() => goto('/packages')}
					className="package-management-button"
					text="Gestão de pacotes"
				/>
			{/if}
			{#if isAdmin}
				<Button on:click={() => goto('/dashboard')} className="dashboard-button" text="Dashboard" />
				
			{/if}
			<Button className="logout-button" text="Sair">Sair</Button>
		{:else}
			<Button onClick={() => goto('/auth')} text="Junta-te"/>
		{/if}
	</div>
</nav>

<style>
</style>
