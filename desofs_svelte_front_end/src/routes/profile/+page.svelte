<script lang="ts">
	import { onMount } from 'svelte';
	import type { UserInfo } from '../../lib/Types/types';
	import Button from '$lib/components/default_components/Header/Button.svelte';

	let fullname: string;
	let email: string;
	let morada: string;
	let nif: string;
	let userId: number;

	export let data: UserInfo;

	onMount(() => {
		console.log(data);
		fullname = data.fullName;
		email = data.username;
		morada = data.morada;
		nif = data.nif;
		userId = data.userId;
	});
</script>

<svelte:head>
	<title>Profile</title>
	<meta name="description" content="Profile Information" />
</svelte:head>

<section class="bg-gray-100 px-20">
	<header class="border rounded bg-white py-4 px-4">
		<h2 class="text-3xl font-bold sm:text-5xl">Profile</h2>
		<div class="mx-auto max-w-screen-xl lg:items-center max-h-screen-xl">
			<div class="mx-auto lg:grid lg:grid-cols-3 gap-5">
				<!-- Lado Esquerdo -->
				<div class="lg:col-span-1 shadow-xl p-2 rounded-md">
					<div class="lg:flex content-center">
						<img
							class="rounded-full h-48 w-48"
							src="https://www.generationsforpeace.org/wp-content/uploads/2018/03/empty.jpg"
							alt="User profile"
						/>
					</div>
					<h1 class="text-pretty font-bold">Nome</h1>
					<p class="text-pretty ml-4 sm:text-lg">{fullname}</p>

					<h1 class=" text-pretty font-bold text-orange-500">Email</h1>
					<p class="text-pretty ml-4 sm:text-lg">{email}</p>

					<h1 class="text-pretty font-bold">Morada de Entrega</h1>
					<p class="text-pretty ml-4 sm:text-lg">{morada}</p>

					<h1 class=" text-pretty font-bold text-orange-500">Numero de Identificação Fiscal</h1>
					<p class="text-pretty ml-4 sm:text-lg">{nif}</p>
				</div>

				<!-- Lado Direito -->
				<div class="lg:col-span-2">
					<!-- Cria-se um card para o lado direito. -->
					<div class="shadow-xl p-2 rounded-md h-full w-1/3">
						<div class="flex flex-col p-9 justify-evenly h-full">
							<Button className="review-button" text="review" gotoName="/profile/reviews" />
							<Button className="Encomendas-button" text="encomendas" gotoName="/profile/reviews" />
							<Button className="change-password-button" text="change password" gotoName="/profile/change-password/{userId}" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
</section>
