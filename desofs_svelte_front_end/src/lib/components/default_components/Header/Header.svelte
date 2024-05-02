<script lang="ts">
	import Button from './Button.svelte';
	import { goto } from '$app/navigation';
	import { loggedIn } from 'C:/Users/rodri/OneDrive/Documents/Mestrado/2Semestre/SISMD/PL3/desofs2024_m1b_2/desofs_svelte_front_end/src/routes/auth/store';
	import { isAdmin } from 'C:/Users/rodri/OneDrive/Documents/Mestrado/2Semestre/SISMD/PL3/desofs2024_m1b_2/desofs_svelte_front_end/src/routes/auth/store';
	import { isDocumentManager } from 'C:/Users/rodri/OneDrive/Documents/Mestrado/2Semestre/SISMD/PL3/desofs2024_m1b_2/desofs_svelte_front_end/src/routes/auth/store';


	// let isMenuOpen = false;
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
		{#if $loggedIn}
			<Button onClick={() => goto('/profile')} className="profile-button" text="Perfil" />
			<Button onClick={() => goto('/Carrinho')} className="order-button" text="Carrinho" />

			{#if isDocumentManager || isAdmin}
				<Button
					onClick={() => goto('/packages')}
					className="package-management-button"
					text="Gestão de pacotes"
				/>
			{/if}
			{#if isAdmin}
				<Button onClick={() => goto('/dashboard')} className="dashboard-button" text="Dashboard" />
			{/if}
			<Button onClick={() => loggedIn.set(false)} className="logout-button" text="Sair">Sair</Button>
		{:else}
			<Button onClick={() => goto('/auth')} text="Junta-te" />
		{/if}
	</div>
</nav>

<style>
</style>
