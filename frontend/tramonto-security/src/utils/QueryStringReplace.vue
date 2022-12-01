<script lang="ts">
import { useRouter } from 'vue-router';

interface Query {
  [key: string]: any;
}
export default {
  setup() {
    const router = useRouter();
    function replaceParam(field: string, value: any) {
      const params = Object.keys(router.currentRoute.value.query);
      const newQuery: Query = {};
      params.forEach((x) => {
        newQuery[x] = (x === field) ? value : router.currentRoute.value.query[x];
      });
      router.push({
        query: { ...newQuery },
      });
    }
    return {
      replaceParam,
    };
  },
};

</script>
