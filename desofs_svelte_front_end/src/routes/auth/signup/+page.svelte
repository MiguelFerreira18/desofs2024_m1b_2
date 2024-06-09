<script lang="ts">
	import { enhance } from '$app/forms';

	let password = '';
	let repeatPassword = '';
	let passwordStrength = 0;
	let barColor = 'red';

	let hasMinLength = false;
	let hasNumber = false;
	let hasUppercase = false;
	let hasSpecialChar = false;
	let passwordsMatch = false;

	let buttonClass = '';
	let isDisabled = false;

	$: {
		if (hasMinLength && hasNumber && hasUppercase && hasSpecialChar && passwordsMatch) {
			buttonClass =
				'group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-amber-600 hover:bg-amber-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-amber-500';
			isDisabled = false;
		} else {
			buttonClass =
				'group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-gray-400 cursor-not-allowed';
			isDisabled = true;
		}
	}

	const updatePasswordStrength = () => {
		hasMinLength = password.length >= 12;
		hasNumber = /\d/.test(password);
		hasUppercase = /[A-Z]/.test(password);
		hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
		// Verificar se a senha contém apenas caracteres imprimíveis Unicode
		if (password != '') {
			passwordsMatch = password === repeatPassword;
		}
		// Calcular a força da senha, incluindo espaços na contagem de caracteres
		const requirementsMet = [
			hasMinLength,
			hasNumber,
			hasUppercase,
			hasSpecialChar,
			passwordsMatch
		].filter(Boolean).length;
		passwordStrength = requirementsMet / 6;

		if (passwordStrength <= 1 / 3) {
			barColor = 'red';
		} else if (passwordStrength <= 2 / 3) {
			barColor = 'yellow';
		} else {
			barColor = 'green';
		}
	};

	const handlePasswordInput = (event: Event) => {
		const target = event.target as HTMLInputElement;
		password = target.value;
		updatePasswordStrength();
	};

	const handleRepeatPasswordInput = (event: Event) => {
		const target = event.target as HTMLInputElement;
		repeatPassword = target.value;
		updatePasswordStrength();
	};

	let passwordVisible = false;

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
					<div class="flex">
						<input
							id="password"
							name="password"
							type="password"
							minlength="12"
							maxlength="128"
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
				<div>
					<label for="repeat-password" class="sr-only">Repeat Password</label>
					<input
						id="repeat-password"
						name="repeat-password"
						type="password"
						minlength="12"
						maxlength="128"
						bind:value={repeatPassword}
						on:input={handleRepeatPasswordInput}
						required
						class="appearance-none rounded-none relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm"
						placeholder="Repeat password"
					/>
				</div>
				<div class="strength-bar" style="margin-top: 0.5rem; margin-bottom: 0.5rem">
					<div style="width: {passwordStrength * 100}%; background-color: {barColor};"></div>
				</div>
				<div style="margin-left: 1rem;">
					<ul class="list-disc">
						<li class={hasMinLength ? 'text-green-500' : ''}>
							Password must have at least 12 characters
						</li>
						<li class={hasNumber ? 'text-green-500' : ''}>Password must have at least 1 number</li>
						<li class={hasUppercase ? 'text-green-500' : ''}>
							Password must have at least 1 uppercase letter
						</li>
						<li class={hasSpecialChar ? 'text-green-500' : ''}>
							Password must have at least 1 special character
						</li>
						<li class={passwordsMatch ? 'text-green-500' : ''}>Passwords must match</li>
					</ul>
				</div>
			</div>
			<div>
				<button type="submit" class={buttonClass} disabled={isDisabled}> Sign in </button>
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

	li {
		font-size: 0.8em;
	}
</style>
