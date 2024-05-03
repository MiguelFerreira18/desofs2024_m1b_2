<script lang="ts">
	import { goto } from '$app/navigation';
	export let className = '';
	export let text = '';
	export let gotoName = '';
</script>

<button
	on:click={() => goto(gotoName)}
	class="{className} inline-block rounded border border-current px-4 py-2 text-sm font-medium"
>
	{text}
</button>
