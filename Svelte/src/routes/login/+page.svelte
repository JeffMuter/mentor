<script lang="ts">
	import { onMount } from 'svelte';
	import { goto } from '$app/navigation';

	let username: string = '';
	let password: string = '';
	let confirmPassword: string = '';
	let role: string = '';

	let isLoginForm: boolean = true;

	function toggleForm() {
		isLoginForm = !isLoginForm;
	}

	const handleSubmit = async (event: Event): Promise<void> => {
		event.preventDefault();
		role = 'user';

		if (isLoginForm) {
			// handle login
			const response = await fetch('http://localhost:9000/login', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ username, password })
			});

			if (response.ok) {
				goto('/');
			}
		} else {
			// handle register
			role = 'user';
			if (password !== confirmPassword) {
				alert('Passwords do not match');
				return;
			}

			const response = await fetch('http://localhost:9000/register', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ username, password, confirmPassword, role })
			});

			if (response.ok) {
				goto('/');
			}
		}
	};
</script>

<main>
	<div>
		<h1>{isLoginForm ? 'Login' : 'Register'}</h1>
		<form>
			<label for="username">Username:</label><br />
			<input bind:value={username} type="text" id="username" name="username" /><br />
			<label for="password">Password:</label><br />
			<input bind:value={password} type="password" id="password" name="password" /><br />
			{#if !isLoginForm}
				<label for="confirmPassword">Confirm Password:</label><br />
				<input
					bind:value={confirmPassword}
					type="password"
					id="confirmPassword"
					name="confirmPassword"
				/><br />
			{/if}
			<input type="submit" value={isLoginForm ? 'Login' : 'Register'} />
		</form>
		<button on:click={toggleForm}>{isLoginForm ? 'Switch to Register' : 'Switch to Login'}</button>
	</div>
</main>
