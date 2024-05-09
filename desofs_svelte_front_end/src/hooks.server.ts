import type { Handle } from '@sveltejs/kit';

export const handle: Handle = async ({ event, resolve }) => {
	// get cookies from browser
	const session = event.cookies.get('authToken');

	if (!session) {
		// if there is no session load page as normal
		return await resolve(event);
	} else {
		event.locals.user = JSON.parse(session);
	}

	return await resolve(event);
};
