const isProduction = process.env.NODE_ENV === 'production';

const devApiConfig = {
	baseUrl: 'http://localhost:9092'
};

const prodApiConfig = {
	baseUrl: 'http://localhost:9092'
};

const apiConfig = isProduction ? prodApiConfig : devApiConfig;

export { apiConfig };
