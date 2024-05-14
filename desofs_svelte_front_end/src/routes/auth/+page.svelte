<script lang="ts">
	import { enhance } from '$app/forms';

	let password = '';
	let passwordVisible = false;

	const handlePasswordInput = (event: Event) => {
		const target = event.target as HTMLInputElement;
		password = target.value.replace(/\s+/g, ' ');
	};

	const togglePasswordVisibility = () => {
		const passwordInput = document.getElementById('password') as HTMLInputElement;
		passwordVisible = !passwordVisible;
		passwordInput.type = passwordVisible ? 'text' : 'password';
	};
</script>

<div class="min-h-screen flex items-center justify-center bg-gray-100 py-12 px-4 sm:px-6 lg:px-8">
	<div class="max-w-md w-full space-y-8 border p-5 bg-white rounded">
		<div>
			<h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
				Sign in to your account
			</h2>
		</div>
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
