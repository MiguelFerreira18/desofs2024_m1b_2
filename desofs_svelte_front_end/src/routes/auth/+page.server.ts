import { fail, redirect } from '@sveltejs/kit';
import type { Actions, PageServerLoad } from './$types';
import { apiConfig } from '../config/api';
import { sendRequest } from '$lib/scripts';

const { baseUrl } = apiConfig;

export const load: PageServerLoad = async ({ locals }) => {
	if (locals.user) {
		redirect(302, '/');
	}
};

export const actions: Actions = {
	async login({ cookies, request }) {
		const form = await request.formData();
		const email = form.get('email');
		const password = form.get('password');

		if (typeof email !== 'string' || typeof password !== 'string' || !email || !password) {
			return fail(400, { invalid: true });
		}

		const body = JSON.stringify({
			username: email,
			password
		})

		const response = await sendRequest('auth/public/login', 'POST', body, '');


		if (!response.ok) {
			return fail(400, { invalid: true });
		} else {
			console.log(response);
		}

		const data = await response.json();
		const token = response.headers.get('Authorization');
		let isAdmin = false;
		let isDocumentManager = false;

		const authorities = data.authorities.map((a: { authority: string }) => a.authority);
		if (authorities.includes('Admin')) {
			isAdmin = true;
		} else if (authorities.includes('DocumentManager')) {
			isDocumentManager = true;
		}

		const cookie = {
			userId: data.userId,
			fullname: data.fullname,
			email: data.username,
			token: token,
			isAdmin: isAdmin,
			isDocumentManager: isDocumentManager
		};

		cookies.set('authToken', JSON.stringify(cookie), {
			path: '/',
			secure: true,
			httpOnly: true,
			sameSite: 'strict'
		});
		return { status: 302, redirect: '/' };
	}
};
