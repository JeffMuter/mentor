<script>
	import { userStore } from '$lib/userStore';

	let storeUsername = '';

	userStore.subscribe((value) => {
		storeUsername = value.storeUsername;
	});
</script>

<p>Welcome {storeUsername}</p>

<style>
</style>
