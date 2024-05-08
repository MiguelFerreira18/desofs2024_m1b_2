import { fail, redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ locals }) => {
    if (!locals.user && (locals.user.isDocumentManager || locals.user.isAdmin)) {
        redirect(302, '/');
    }
}