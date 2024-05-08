import { fail, redirect } from '@sveltejs/kit'
import type { PageServerLoad } from './$types'

export const load: PageServerLoad = async ({ locals }) => {
    if (!locals.user) {
        redirect(302, '/');
    }

    const response = await fetch('http://localhost:9092/user/info/' + locals.user.userId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': '*/*'
        }
    })

    if (!response.ok) {
        fail(400, { invalid: true });
    }

    const perfil = await response.json();

    return perfil;

}

// export const load: PageLoad = async ({ locals }) {
//     if (!locals.user) {
//         redirect(302, '/');
//     }
//     const response = await fetch('http://localhost:9092/user/info/' + locals.user.userId, {
//         method: 'GET',
//         headers: {
//             'Content-Type': 'application/json',
//             'Accept': '*/*'
//         }
//     })

//     if (!response.ok) {
//         fail(400, { invalid: true });
//     }

//     const perfil = await response.json();

//     return perfil;
// }