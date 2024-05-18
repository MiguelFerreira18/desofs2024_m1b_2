# Step 1: Start from a Node.js base image
FROM node:latest

# Step 2: Set the working directory in the Docker image
WORKDIR /app

# Step 3: Copy package.json and package-lock.json
COPY package*.json ./

# Step 4: Install dependencies
RUN npm install

# Step 5: Copy the rest of your application
COPY . .

# Step 6: Build your SvelteKit application
RUN npm run build

# Step 7: Expose the port your application runs on
EXPOSE 5173

# Step 8: Define the command to run your application
CMD [ "npm", "run" , "dev" , "--" , "--host" ]