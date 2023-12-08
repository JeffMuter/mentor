<script>
	import { onMount } from 'svelte';
	import * as SpeechSDK from 'microsoft-cognitiveservices-speech-sdk';

	let recording = false;
	let recordedInput = 'Tell my mentor: ';

	const startRecording = () => {
		recording = true;
	};
	const stopRecording = () => {
		recording = false;
	};
</script>

// TODO implement the voicerecorder functions, get API keys and env setup for it.
<section class="voice-section">
	<h2>Welcome to MentorBot.ai</h2>
	<h3>Hit the button to start a conversation with mentor0!</h3>
	<button
		on:click={recording ? stopRecording : startRecording}
		class="record-button"
		class:record-button-red={recording}
	/>
	<p>{recordedInput}</p>
</section>

<style>
	.voice-section {
		width: 100%;
		height: 90vw;
	}
	.record-button {
		height: 4vw;
		width: 4vw;
		border-radius: 50%;
		background-color: green;
	}
	.record-button-red {
		background-color: red;
	}
</style>
