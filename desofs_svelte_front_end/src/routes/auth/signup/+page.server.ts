import { fail, redirect } from '@sveltejs/kit';
import type { Action, Actions, PageServerLoad } from './$types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ locals }) => {
	if (locals.user) {
		redirect(302, '/');
	}
};

const signup: Action = async ({ request }) => {
	const data = await request.formData();
	const email = data.get('email');
	const fullname = data.get('fullname');
	const nif = data.get('nif');
	const morada = data.get('morada');
	const password = data.get('password');
	const repeatPassword = data.get('repeat-password');
	if (
		typeof email !== 'string' ||
		typeof fullname !== 'string' ||
		typeof nif !== 'string' ||
		typeof morada !== 'string' ||
		typeof password !== 'string' ||
		typeof repeatPassword !== 'string' ||
		!email ||
		!fullname ||
		!nif ||
		!morada ||
		!password ||
		!repeatPassword
	) {
		return fail(400, { invalid: true });
	}
	const userData: string = JSON.stringify({
		username: email,
		password: password,
		fullName: fullname,
		nif: nif,
		morada: morada
	});

	if (password.length < 12) {
		return fail(400, { passwordError: 'Password must be at least 12 characters long' });
	} else if (password.length > 128) {
		return fail(400, { passwordError: 'Password must be less than 128 characters long' });
	}

	await sendRequest(`auth/public/signup`, 'POST', userData, '');

	redirect(303, '/auth');
};

export const actions: Actions = { signup };
