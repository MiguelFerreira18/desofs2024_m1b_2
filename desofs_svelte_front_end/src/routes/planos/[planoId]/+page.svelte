<script lang="ts">
	import type { PageData } from './$types';
	import { goto } from '$app/navigation';
	export let data: PageData;

	

	
	

	

</script>
{#if data.pacote && !data.pacote.disabled }
<div class="bg-gray-100 px-20 pt-10 min-h-screen ">
	<div class="border rounded bg-white px-20 py-12 flex flex-row items-center max-w-6xl self-center">
		<div
			class="w-1/2 my-auto max-w-md border-2 border-gray-200 rounded-lg shadow-lg p-5 bg-slate-100"
		>
			<!-- Header of card -->

			<div class="flex flex-col items-center">
				<h1 class="text-3xl font-bold text-center">{data.pacote.nome}</h1>
			</div>

			<!-- Body of card -->
			<div class="mt-20 p-5">
				<div class="flex flex-col items-center">
					<h1 class="text-2xl font-bold text-center">Descrição</h1>
					<p class="text-center">{data.pacote.pacoteDescription}</p>
				</div>
				<div class="flex flex-col items-center mt-10">
					<h1 class="text-2xl font-bold text-center">Valor</h1>
					<p class="text-center">{data.pacote.pacoteBasePrice} €</p>
				</div>
				<div class="flex flex-col items-center mt-10">
					<h1 class="text-2xl font-bold text-center">Tipo pacote</h1>
					<p class="text-center">{data.pacote.tipoPacote.nome}</p>
				</div>
			</div>
		</div>
		<div class="w-1/2 flex flex-col gap-10">
			<div class="flex justify-center ml-auto">
				<button class="inline-block rounded border border-current px-4 py-2 text-sm "
					>Receitas</button
				>
			</div>
            <div class="flex justify-center ml-auto">
			<button class="inline-block rounded border border-current px-4 py-2 text-sm ">Comprar</button>
            </div>
        </div>
	</div>
</div>
{:else}
<h1>Esse pacote foi não existe</h1>
{/if}