<script lang="ts">
	import Button from './Button.svelte';

	export let data;

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
		{#if data.user}
			<Button className="profile-button" text="Perfil" gotoName="/profile" />
			<Button className="order-button" text="Carrinho" gotoName="/carrinho" />
			<Button className="enc-button" text="Encomenda" gotoName="/encomenda" />
			{#if data.user.isAdmin || data.user.isDocumentManager}
				<Button
					className="package-management-button"
					text="Gestão de pacotes"
					gotoName="/package-management"
				/>
			{/if}
			{#if data.user}
				<Button className="dashboard-button" text="Dashboard" gotoName="/dashboard" />
			{/if}
			<Button className="logout-button" text="Sair" gotoName="/auth/logout" />
		{:else}
			<Button className="login-button" text="Junta-te" gotoName="/auth" />
		{/if}
	</div>
</nav>

<style>
</style>
