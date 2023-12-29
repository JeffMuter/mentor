<script>
	let isLoginForm = true;

	function toggleForm() {
		isLoginForm = !isLoginForm;
	}
</script>

<main>
	<div>
		<h1>{isLoginForm ? 'Login' : 'Register'}</h1>
		<form>
			<label for="username">Username:</label><br />
			<input type="text" id="username" name="username" /><br />
			<label for="password">Password:</label><br />
			<input type="password" id="password" name="password" /><br />
			{#if !isLoginForm}
				<label for="confirmPassword">Confirm Password:</label><br />
				<input type="password" id="confirmPassword" name="confirmPassword" /><br />
			{/if}
			<input type="submit" value={isLoginForm ? 'Login' : 'Register'} />
		</form>
		<button on:click={toggleForm}>{isLoginForm ? 'Switch to Register' : 'Switch to Login'}</button>
	</div>
</main>
