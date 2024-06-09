<script lang="ts">
	import { enhance } from '$app/forms';
	import { page } from '$app/stores';

	let password = '';
	let passwordVisible = false;
	let message = '';
	let showAlert = false;

	const handlePasswordInput = (event: Event) => {
		const target = event.target as HTMLInputElement;
		password = target.value.replace(/\s+/g, ' ');
	};

	const togglePasswordVisibility = () => {
		const passwordInput = document.getElementById('password') as HTMLInputElement;
		passwordVisible = !passwordVisible;
		passwordInput.type = passwordVisible ? 'text' : 'password';
	};

	const closeAlert = () => {
		showAlert = false;
	};

	// Listen to the page store to get the form submission response
	$: if ($page.form && $page.form.message) {
		message = $page.form.message;
		showAlert = message === 'Error';
	}
</script>

<div class="min-h-screen flex items-center justify-center bg-gray-100 py-12 px-4 sm:px-6 lg:px-8">
	

	<div class="max-w-md w-full space-y-8 border p-5 bg-white rounded">
		<div>
			<h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
				Sign in to your account
			</h2>
		</div>

		<!-- Alert message -->
		{#if showAlert}
		<div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
			<strong class="font-bold">Error!</strong>
			<span class="block sm:inline">Incorrect Password. Please try again.</span>
			<button class="absolute top-0 bottom-0 right-0 px-4 py-3" on:click={closeAlert}>
				<svg class="fill-current h-6 w-6 text-red-500 cursor-pointer" role="button" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20">
					<title>Close</title>
					<path d="M14.348 14.849a1 1 0 01-1.415 0L10 11.415l-2.933 2.934a1 1 0 01-1.415-1.415L8.585 10 5.652 7.067a1 1 0 011.415-1.415L10 8.585l2.933-2.933a1 1 0 011.415 1.415L11.415 10l2.933 2.933a1 1 0 010 1.415z"/>
				</svg>
			</button>
		</div>
		{/if}

		<form class="mt-8 space-y-6 flex flex-col" use:enhance method="post" action="?/login">
			<div class="rounded-md shadow-sm -space-y-px">
				<div>
					<label for="email-address" class="sr-only">Email address</label>
					<input
						id="email-address"
						name="email"
						type="email"
						required
						class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 rounded-t-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
						placeholder="Email address"
					/>
				</div>
				<div>
					<label for="password" class="sr-only">Password</label>
					<div class="flex">
						<input
							id="password"
							name="password"
							type="password"
							bind:value={password}
							on:input={handlePasswordInput}
							required
							class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
							placeholder="Password"
						/>
						<button
							type="button"
							class="ml-2 px-2 py-1 rounded-md bg-gray-200 text-gray-700"
							on:click={togglePasswordVisibility}
						>
							{passwordVisible ? 'Hide' : 'Show'}
						</button>
					</div>
				</div>
			</div>

			<div>
				<button
					type="submit"
					class="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-amber-600 hover:bg-amber-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-amber-500"
				>
					Sign in
				</button>
			</div>
			<a href="/auth/signup" class="self-center text-orange-400 hover:text-orange-500">
				Ainda não tens conta?
			</a>
		</form>
	</div>
</div>
