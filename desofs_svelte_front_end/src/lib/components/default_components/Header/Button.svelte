<script lang="ts">
    export let onClick: () => void = () => {};
    export let className = '';
    export let text = '';
  </script>
  
  <button on:click={onClick} class="{className} inline-block rounded border border-current px-4 py-2 text-sm font-medium">
    {text}
  </button>