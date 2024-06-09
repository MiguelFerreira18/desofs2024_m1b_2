import type { PageServerLoad, Actions } from '../$types';
import { redirect, fail } from '@sveltejs/kit';
import { sendRequest } from '$lib/scripts';

export const load: PageServerLoad = async ({ params, locals }) => {
    if (!locals.user) {
        redirect(302, '/');
    }
    const user: string = params.userId;
};

export const actions: Actions = {
    changePassword: async ({ request, locals }) => {
        const form = await request.formData();
        const oldPassword = form.get('oldPassword');
        const newPassword = form.get('newPassword');
        const confirmPassword = form.get('confirmPassword');
        const username = locals.user.email;

        if (typeof oldPassword !== 'string' || typeof newPassword !== 'string' || typeof confirmPassword !== 'string') {
            return fail(400, { invalid: true });
        }

        if (newPassword !== confirmPassword) {
            return fail(400, { invalid: true });
        }

        const body = JSON.stringify({
            username,
            oldPassword,
            newPassword,
        });

        console.log(body);


        const response = await sendRequest(
            `user/change-password/${locals.user.userId}`,
            'POST',
            body,
            locals.user.token
        );

        if(!response.ok) {
            console.log(response.status);
            console.log(response.statusText);
            return fail(400, { invalid: true });
        } else {
            throw redirect(302, '/profile');
        }
    }
};