import { apiConfig } from '../routes/config/api';

const { baseUrl } = apiConfig;
type RequestOptions = {
	method: string;
	headers: { [key: string]: string };
	body?: string;
};

/**
 * Sends an asynchronous HTTP request to the specified path with the given method, data, and token.
 *
 * @param {string} path - The path of the request.
 * @param {string} method - The HTTP method of the request.
 * @param {string} data - The data to be sent in the request body.
 * @param {string} token - The token to be included in the request headers for authentication.
 * @return {Promise<Response>} - A Promise that resolves to the Response object representing the server's response to the request.
 */
async function sendRequest(path: string, method: string, data: string, token: string) {
	const headers: { [key: string]: string } = {
		'Content-Type': 'application/json'
	};

	if (token !== '') {
		headers['Authorization'] = `Bearer ${token}`;
	}

	const options: RequestOptions = {
		method: method,
		headers: headers
	};

	if (['POST', 'PATCH', 'PUT'].includes(method.toUpperCase()) && data !== '') {
		options.body = data;
	}

	const response = await fetch(`${baseUrl}/${path}`, options);

	return response;
}

export { sendRequest };
