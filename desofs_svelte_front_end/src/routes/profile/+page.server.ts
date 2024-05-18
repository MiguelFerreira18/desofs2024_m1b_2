import { fail, redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ locals }) => {
	if (!locals.user) {
		redirect(302, '/');
	}

	const response = await sendRequest(
		`user/info/${locals.user.userId}`,
		'GET',
		'',
		locals.user.token
	);

	if (!response.ok) {
		fail(400, { invalid: true });
	}

	const perfil = await response.json();

	return perfil;
};
