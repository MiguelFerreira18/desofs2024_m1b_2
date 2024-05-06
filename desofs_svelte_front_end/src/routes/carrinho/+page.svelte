<script lang="ts">
	import { goto } from '$app/navigation';
	import { loggedIn } from '../auth/store';

    $: if ($loggedIn) {
        goto('/');
    }

</script>