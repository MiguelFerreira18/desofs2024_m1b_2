<script lang="ts">
	import Button from './Button.svelte';
	import { goto } from '$app/navigation';
    import { page } from '$app/stores';

	// console.log(page.data.user);
	
</script>

<nav class="flex justify-evenly flex-row bg-white border-gray-200 px-24 py-4">
	<div class="flex flex-row self-center gap-2 mr-auto">
		<a href="/"><p>logo</p></a>
		<a href="/"><p>Cozinha na Cozinha</p></a>
	</div>

	<ul class="flex justify-center self-center flex-row gap-8 mr-auto">
		<li><a href="/planos">Planos</a></li>
		<li><a href="/planos">Sobre nós</a></li>
	</ul>

	<div class="self-center">
        {#if $page.data.user}
			<Button onClick={() => goto('/profile')} className="profile-button" text="Perfil" />
			<Button onClick={() => goto('/carrinho')} className="order-button" text="Carrinho" />

			{#if $page.data.user.isAdmin || $page.data.user.isDocumentManager}
				<Button
					onClick={() => goto('/packages')}
					className="package-management-button"
					text="Gestão de pacotes"
				/>
			{/if}
			{#if $page.data.user}
				<Button onClick={() => goto('/dashboard')} className="dashboard-button" text="Dashboard" />
			{/if}
			<Button onClick={() => goto("/auth/logout")} className="logout-button" text="Sair">Sair</Button>
		{:else}
			<Button onClick={() => goto('/auth')} text="Junta-te" />
		{/if}
	</div>
</nav>

<style>
</style>
