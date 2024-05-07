import { redirect } from '@sveltejs/kit'
import type { Actions, PageServerLoad } from './$types'

export const load: PageServerLoad = async ( locals ) => {
    locals.cookies.set('authToken', '', {
        path: '/',
        expires: new Date(0),
      })
    redirect(302, '/')
}

// export const actions: Actions = {
//   default({ cookies }) {
//     // eat the cookie
//     cookies.set('authToken', '', {
//       path: '/',
//       expires: new Date(0),
//     })

//     // redirect the user
//     redirect(302, '/auth')
//   },
// }
