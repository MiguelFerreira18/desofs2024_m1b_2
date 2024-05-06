import { fail, redirect } from '@sveltejs/kit';
import type { Action, Actions, PageServerLoad } from './$types'

export const load: PageServerLoad = async ({ locals }) => {
    if (locals.user) {
        redirect(302, '/');
    }
}

const signup: Action = async ({ request}) => {
    const data = await request.formData();
    const email = data.get('email')
    const fullname = data.get('fullname')
    const nif = data.get('nif')
    const morada = data.get('morada')
    const password = data.get('password')
    const repeatPassword = data.get('repeatPassword')

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

    const response = await fetch('http://localhost:9092/auth/public/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': '*/*'
        },
        body: JSON.stringify({
            username: email,
            password: password,
            fullName: fullname,
            nif: nif,
            morada: morada
        })
    });

    redirect(303, '/auth/login')
}

export const actions: Actions = { signup }