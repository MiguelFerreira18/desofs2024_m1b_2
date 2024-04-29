<script lang="ts">
	export let key: number;
</script>

<div class="py-10">
	<div
		class="grid grid-cols-1 grid-rows-2 max-w-sm rounded-sm border border-gray-200 bg-white shadow-lg justify-center items-center p-4"
	>
		<div class="grid grid-cols-4">
			<div class="flex flex-col items-center p-2 col-span-1">
				<div class="rounded-full bg-slate-800 h-14 w-14"></div>
				<p>{key}</p>
			</div>
			<div class="px-5 pb-2 col-span-3">
				<p class="text-gray-600 text-xs">
					Put toy mouse in food bowl run out of litter box at full speed drool but pee in the shoe
					purr when being pet but chew foot.
				</p>
			</div>
		</div>

		<div>
			<hr class="h-px bg-gray-300 border-0 dark:bg-gray-700" />
			<div class="flex justify-center items-center pt-4">*****</div>
		</div>
	</div>
</div>
