<script lang="ts">
	import { userStore } from '$lib/userStore';
	import { onMount } from 'svelte';

	let storeUsername: string = '';
	let mentors: { id: number; name: string }[] = [];

	userStore.subscribe((value: { storeUsername: string }) => {
		storeUsername = value.storeUsername;
	});

	const createMentorList = (data: { id: number; name: string }[]): void => {
		mentors = data;
	};

	const getMentorList = async (): Promise<void> => {
		try {
			const response = await fetch('http://localhost:9000/mentorList', {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json'
				}
			});

			if (response.ok) {
				const data: { id: number; name: string }[] = await response.json();
				createMentorList(data);
			} else {
				console.log('error');
			}
		} catch (error) {
			console.error(error);
		}
	};

	onMount(getMentorList);
</script>

<p>Welcome {storeUsername}</p>

<section id="mentorList">
	<h2>Mentor List</h2>
	<ul>
		{#each mentors as mentor (mentor.id)}
			<li>{mentor.name}</li>
		{/each}
	</ul>
</section>

<style>
</style>
