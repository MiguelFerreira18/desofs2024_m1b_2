import type { Handle } from '@sveltejs/kit'

export const handle: Handle = async ({ event, resolve }) => {
  // get cookies from browser
  const session = event.cookies.get('authToken')

  if (!session) {
    // if there is no session load page as normal
    return await resolve(event)
  } else {
    // console.log(session)
    event.locals.user = JSON.parse(session)
  }

  // if `user` exists set `events.local`
  // if (user) {
  //   event.locals.user = {
  //     name: user.username,
  //     role: user.role.name,
  //   }
  // }

  // load page as normal
  return await resolve(event)
}
