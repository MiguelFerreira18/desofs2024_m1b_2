<script lang="ts">
	export let disabled: boolean;
</script>

<div
	class="relative grid font-bold uppercase whitespace-nowrap select-none {disabled
		? 'bg-red-500/20'
		: 'bg-green-500/20'} {disabled
		? 'text-red-700'
		: 'text-green-600'} py-1 px-2 text-xs rounded-md"
	style="opacity: 1;"
>
	<span class="text-center">{disabled ? 'Disabled' : 'Enabled'} </span>
</div>
