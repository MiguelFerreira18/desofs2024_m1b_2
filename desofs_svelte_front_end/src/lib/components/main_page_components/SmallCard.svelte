<script lang="ts">
	import type { Review } from '$lib/Types/types';

	export let review: Review;
</script>

<div class="py-10">
	<div
		class="grid grid-cols-1 grid-rows-2 max-w-sm rounded-sm border border-gray-200 bg-white shadow-lg justify-center items-center p-4"
	>
		<div class="flex justify-center">
			<div class="px-5 pb-2 col-span-3">
				<p class="text-gray-600 text-xs text-center">
					{#if review}
						{review.reviewText}
					{/if}
				</p>
			</div>
		</div>

		<div>
			<hr class="h-px bg-gray-300 border-0 dark:bg-gray-700" />
			<div class="flex justify-center items-center pt-4">
				{#if review}
					<!-- eslint-disable-next-line @typescript-eslint/no-unused-vars -->
					{#each { length: review.rating } as _}
						<i class="fa-solid fa-star"></i>
					{/each}
				{/if}
			</div>
		</div>
	</div>
</div>
