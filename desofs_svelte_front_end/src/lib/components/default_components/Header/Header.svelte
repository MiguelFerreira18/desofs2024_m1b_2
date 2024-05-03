<script lang="ts">
	import Button from './Button.svelte';
	let loggedIn = true;
	let isAdmin = true;
	let isDocumentManager = true;

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
		{#if loggedIn}
			<Button gotoName="/profile" className="profile-button" text="Perfil" />
			<Button gotoName="/carrinho" className="order-button" text="Carrinho" />

			{#if isDocumentManager || isAdmin}
				<Button
					className="package-management-button"
					text="Gestão de pacotes"
					gotoName="/package-management"
				/>
			{/if}
			{#if isAdmin}
				<Button gotoName="/dashboard" className="dashboard-button" text="Dashboard" />
			{/if}
			<Button gotoName="/logout" className="logout-button" text="Sair">Sair</Button>
		{:else}
			<Button gotoName="/login" className="login-button" text="Junta-te" />
		{/if}
	</div>
</nav>

<style>
</style>
