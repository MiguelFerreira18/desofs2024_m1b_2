import { apiConfig } from '../routes/config/api';

const { baseUrl } = apiConfig;
type RequestOptions = {
	method: string;
	headers: { [key: string]: string };
	body?: string;
};

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
