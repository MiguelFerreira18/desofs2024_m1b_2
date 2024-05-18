import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async (locals) => {
	locals.cookies.set('authToken', '', {
		path: '/',
		expires: new Date(0)
	});
	redirect(302, '/');
};
