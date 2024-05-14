<script lang="ts">
	import { enhance } from '$app/forms';
	let password = '';
	let passwordLength = 0;
	let passwordStrength = 0;
	let barColor = 'red';

	const updatePasswordStrength = (password: string) => {
		passwordLength = password.length;
		passwordStrength = Math.min(passwordLength / 12, 1);

		if (passwordStrength <= 1 / 3) {
			barColor = 'red';
		} else if (passwordStrength < 1) {
			barColor = 'yellow';
		} else {
			barColor = 'green';
		}
	};

	const handleInput = (event: Event) => {
			const target = event.target as HTMLInputElement;
			updatePasswordStrength(target.value);
		};
</script>

<div class="min-h-screen flex items-center justify-center bg-gray-100 py-12 px-4 sm:px-6 lg:px-8">
	<div class="max-w-md w-full space-y-8 border p-5 bg-white rounded">
		<div>
			<h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
				Sign in to your account
			</h2>
		</div>
		<form class="mt-8 space-y-6 flex flex-col" use:enhance action="?/signup" method="POST">
			<div class="rounded-md shadow-sm -space-y-px">
				<div>
					<label for="email-address" class="sr-only">Email address</label>
					<input
						id="email-address"
						name="email"
						type="email"
						required
						class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
						placeholder="Email"
					/>
				</div>
				<div>
					<label for="fullName" class="sr-only">FullName</label>
					<input
						id="fullname"
						name="fullname"
						type="text"
						required
						class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
						placeholder="Full name"
					/>
				</div>
			</div>
			<div class="rounded-md shadow-sm -space-y-px">
				<div>
					<label for="nif" class="sr-only">Nif</label>
					<input
						id="nif"
						name="nif"
						type="text"
						required
						class="appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
						placeholder="NIF"
					/>
				</div>
				<div>
					<label for="morada" class="sr-only">Morada</label>
					<input
						id="morada"
						name="morada"
						type="text"
						required
						class="appearance-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
						placeholder="Morada"
					/>
				</div>
			</div>
			<div class="rounded-md shadow-sm -space-y-px">
				<div>
					<label for="password" class="sr-only">Password</label>
					<input
						id="password"
						name="password"
						type="password"
						minlength="12"
						maxlength="128"
						bind:value={password}
						on:input={handleInput}
						required
						class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
						placeholder="Password"
					/>
				</div>
				<div class="strength-bar" style="margin-top: 0.5rem; margin-bottom: 0.5rem">
					<div style="width: {passwordStrength * 100}%; background-color: {barColor};"></div>
				</div>
				<div>
					<label for="repeat-password" class="sr-only">Repeat Password</label>
					<input
						id="repeat-password"
						name="repeat-password"
						type="password"
						minlength="12"
						maxlength="128"
						required
						class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
						placeholder="Repeat password"
					/>
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
		</form>
	</div>
</div>

<style>
	.strength-bar {
		width: 100%;
		height: 5px;
		background-color: lightgray;
		margin-top: 10px;
	}
	.strength-bar > div {
		height: 100%;
		transition:
			width 0.3s ease,
			background-color 0.3s ease;
	}
</style>
